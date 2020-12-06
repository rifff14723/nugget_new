'''
requirements -
pip install audioclipextractor
pip install wget
pip install configparse
'''

'''
usage 
python snipper.py -s 13.5 
                -e 15.6 
                -u "https://www.listennotes.com/e/p/86a160b091b848cbbdaf7b6ad95f2e6c/" 
                -f '/Users/dhruval.rana/Downloads/ffmpeg' 
                -d '/Users/dhruval.rana/Downloads/work/Videos/Nugget/thinkpod/utils/downloads/' 
                -c '/Users/dhruval.rana/Downloads/work/Videos/Nugget/thinkpod/utils/clips'
'''

from audioclipextractor import AudioClipExtractor, SpecsParser
import configparse
import wget
import os
import sys, getopt
import requests
from subprocess import call


def main(argv):
    start_time = ''
    end_time = ''
    url = ''
    clip_name = "clip1"

    ffmpeg_path = './ffmpeg'

    if not os.path.exists('downloads'):
        os.makedirs('downloads')
    save_path = 'downloads/'

    if not os.path.exists('clips'):
        os.makedirs('clips')
    clip_save_path = 'clips/'

    try:
        opts, args = getopt.getopt(argv,"hs:e:u:f:d:c:n:",["startTime=","endTime=","audioURL=","FFMPEGpath=","pathAudioDownload=","pathClipSave=","clipTitle="])
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
        elif opt in ("-e", "--endTime"):
            end_time= arg
        elif opt in ("-u", "--audioURL"):
            url= arg
        elif opt in ("-f", "--FFMPEGpath"):
            ffmpeg_path = arg
        elif opt in ("-d", "--pathAudioDownload"):
            save_path= arg
        elif opt in ("-c", "--pathClipSave"):
            clip_save_path= arg
        elif opt in ("-n", "--clipTitle"):
            clip_name = arg

    filename  = url.split("/")[-2] + '.mp3'

    save_as = save_path + filename

    if not os.path.exists(save_as):
        wget.download(url, save_as)

    if(save_as.split(".")[-1] == 'mp3'):
        # Initialize the extractor
        ext = AudioClipExtractor(save_as, ffmpeg_path)
    else:
        print("PODCAST AUDIO FILE NOT DOWNLOADED")

    if  float(start_time) < float(end_time):
        specs = start_time + " " + end_time
        ext.extract_clips(specs, clip_save_path, zip_output=False)
    else:
        print("START TIME CANNOT BE MORE THAN END TIME")

    snippet_save_path = 'clips/' + clip_name + '.mp3'
    snippet_mp4_save_path = '/Users/dhruval.rana/IdeaProjects/nugget/clips/' + clip_name + '.mp4'

    if os.path.exists('clips/clip1.mp3'):
        os.rename('clips/clip1.mp3',snippet_save_path)
    else:
        print("SNIPPET HAS NOT BEEN CREATED")


    if os.path.exists(snippet_save_path):
        # uncomment to make post request
        # r = requests.post("http://localhost:8080/tasks/"+clip_name, params={"action":"changeTaskStatus", "taskStatus":"SUCCESS"})
        # print(r, r.json(), r.content)

        # create mp4 from mp3, wget thumbnail and add it here
        # wget thumbnail
        thumbnail_path = 'thumbnail.jpg'
        call("./ffmpeg -loop 1 -i " +  thumbnail_path + " -i " + snippet_save_path + " -shortest -c:a copy " + snippet_mp4_save_path, shell=True)

    else:
        requests.post("http://localhost:8080/tasks/"+clip_name, params={"action":"changeTaskStatus", "taskStatus":"FAILURE"})




if __name__ == "__main__":
    main(sys.argv[1:])