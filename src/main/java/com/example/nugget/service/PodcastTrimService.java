package com.example.nugget.service;

import com.example.nugget.ResponseDTO.PodcastTrimRequestDTO;
import com.example.nugget.StringUtils;
import com.example.nugget.model.PodcastTrimTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
public class PodcastTrimService {
    private String snipperLocation = "../utilityPython/snipper.py" ;

    @Autowired
    PodcastTrimTaskGenerator taskGenerator ;


    public UUID trim(PodcastTrimRequestDTO podcastTrimDTO)  {

        try {
            //PodcastTrimTaskParam podcastTrimTaskParam = new PodcastTrimTaskParam() ;
            final PodcastTrimTaskRequest req = this.taskGenerator.createTaskRequest(podcastTrimDTO);
            System.out.println(req.getId());
            runPythonFile(podcastTrimDTO, req.getId()) ;
            return req.getId();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }

    private void runPythonFile(PodcastTrimRequestDTO podcastTrimDTO, UUID taskID) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();

        String start = podcastTrimDTO.getStartTime().toString() ;
        String end = podcastTrimDTO.getEndTime().toString() ;
        String url =   podcastTrimDTO.getPodcastUrl() ;
        String imageUrl = podcastTrimDTO.getImageUrl();
        System.out.println(podcastTrimDTO.toString());
        processBuilder.command(StringUtils.python,
                StringUtils.snipperLocation,
                "-s", start,
                "-e",end,
                "-f", StringUtils.ffmpegPath,
                "-d" , StringUtils.episodeDownloadPath,
                "-c" , StringUtils.clipsDownloadPath,
                "-u", url,
                "-n", taskID.toString() ,
                 "-i" , imageUrl);


        try {
            Process process = processBuilder.start();
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
