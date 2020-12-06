'''
requirements -
pip install audioclipextractor
pip install wget
pip install configparse
'''

'''
usage 
python snipper.py -s 13.5 -e 15.6 -u "https://www.listennotes.com/e/p/86a160b091b848cbbdaf7b6ad95f2e6c/" 

'''

from audioclipextractor import AudioClipExtractor, SpecsParser
import configparse
import wget
import os
import sys, getopt
import requests
from subprocess import call

task_success = "SUCCESS" 
task_failed = "FAILED" 

def changeTaskStatus(task_uuid, status): 
    r = requests.post("http://localhost:8080/tasks/"+task_uuid, params={"action":"changeTaskStatus", "taskStatus":status})
    return r ; 

def create_download_clips_folders(episode_download_path, snipped_audio_path): 
    if not os.path.exists(episode_download_path):
        os.makedirs(episode_download_path)
    save_path = episode_download_path + '/'

    if not os.path.exists(snipped_audio_path):
        os.makedirs(snipped_audio_path)
    clip_save_path = snipped_audio_path + '/'

def create_video_snippet(audio_snippet_full_path,  video_snippet_mp4_save_path , var_map):
     
      
    if os.path.exists(var_map['snipped_audio_path'] + '/clip1.mp3' ):
        os.rename(var_map['snipped_audio_path'] + '/clip1.mp3',audio_snippet_full_path)
    else:
        print("SNIPPET HAS NOT BEEN CREATED")
        changeTaskStatus(var_map['task_uuid'], "FAILED") ; 
        return 
    
    if os.path.exists(audio_snippet_full_path):
        thumbnail_path  = var_map['episode_download_path'] + '/' + var_map['task_uuid'] +'.jpg' 
        print("Path of downloaded thumbnail" + thumbnail_path)
        wget.download(var_map['image_url'], thumbnail_path ) 
        
        call(var_map['ffmpeg_path'] + " -loop 1 -i " +  thumbnail_path + " -i " + audio_snippet_full_path + " -shortest -c:a copy " + video_snippet_mp4_save_path, shell=True)
        r = changeTaskStatus(var_map['task_uuid'], task_success) ; 
        print(r, r.json(), r.content)

    else:
        changeTaskStatus(var_map['task_uuid'], task_failed) ; 
        return 


def create_variable_map(argv):
    var_map = dict() 
    try:
        opts, args = getopt.getopt(argv,"hs:e:u:f:d:c:n:i:",["startTime=","endTime=","audioURL=","FFMPEGpath=","pathAudioDownload=","pathClipSave=","clipTitle=","image="])
    except getopt.GetoptError:
        #print("SOME ERROR")
        #print('python snipper.py -s <startTime> -e <endTime> -u <audioURL> -f <FFMPEGpath> -d <pathAudioDownload> -c <pathClipSave> -n <clipTitle>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('python snipper.py -s <startTime> -e <endTime> -u <audioURL> -f <FFMPEGpath> -d <pathAudioDownload> -c <pathClipSave> -n <clipTitle>')
            sys.exit()
        elif opt in ("-s", "--startTime"):
            start_time = arg
            var_map['start_time'] = start_time
        elif opt in ("-e", "--endTime"):
            end_time= arg
            var_map['end_time'] = end_time
        elif opt in ("-u", "--episode_url"):
            episode_url= arg
            var_map['episode_url'] = episode_url
        elif opt in ("-f", "--FFMPEGpath"):
            ffmpeg_path = arg
            var_map['ffmpeg_path'] = ffmpeg_path
        elif opt in ("-d", "--episode_download_path"):
            episode_download_path= arg
            var_map['episode_download_path'] = episode_download_path
        elif opt in ("-c", "--snipped_audio_path"):
            snipped_audio_path= arg
            var_map['snipped_audio_path'] = snipped_audio_path
        elif opt in ("-n", "--task_uuid"):
            task_uuid = arg
            var_map['task_uuid'] = task_uuid
        elif opt in ("-i", "--image_url"):
            image_url = arg
            var_map['image_url'] = image_url
    print("Variable Map")
    print(var_map) ; 
    return var_map


def main(argv):
     
    
    var_map = create_variable_map(argv) 
    create_download_clips_folders(var_map['episode_download_path'] , var_map['snipped_audio_path']) ; 

    filename  = var_map["episode_url"].split("/")[-2] + '.mp3'

    episode_download_full_path = var_map["episode_download_path"] + "/" + filename

    print("downloading episode at" + episode_download_full_path)  ;  

    if not os.path.exists(episode_download_full_path):
        wget.download(var_map["episode_url"], episode_download_full_path)
    print("Downloaded episode")  

    if(episode_download_full_path.split(".")[-1] == 'mp3'):
        # Initialize the extractor
        ext = AudioClipExtractor(episode_download_full_path, var_map['ffmpeg_path'])
        print("Created snippet mp3") 
    else:
        print("PODCAST AUDIO FILE NOT DOWNLOADED")
        changeTaskStatus(var_map['task_uuid'], task_failed)
        return 
    

    if  float(var_map['start_time']) < float(var_map['end_time']):
        specs = var_map['start_time'] + " " + var_map['end_time']
        ext.extract_clips(specs, var_map['snipped_audio_path'], zip_output=False)
    else:
        print("START TIME CANNOT BE MORE THAN END TIME")
        changeTaskStatus(var_map['task_uuid'], task_failed)
        return 

    audio_snippet_full_path = var_map['snipped_audio_path'] + '/' + var_map['task_uuid'] + '.mp3'
    video_snippet_full_path = var_map['snipped_audio_path'] + '/' + var_map['task_uuid'] + '.mp4'
    print("Full path of audio snippet" +  audio_snippet_full_path) ; 
    print("Full path of video snippet" +  video_snippet_full_path) ; 
    create_video_snippet(
        audio_snippet_full_path, 
        video_snippet_full_path, 
        var_map
    )

    

if __name__ == "__main__":
    main(sys.argv[1:])