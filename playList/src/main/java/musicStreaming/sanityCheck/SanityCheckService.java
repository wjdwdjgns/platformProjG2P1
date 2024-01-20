package musicStreaming.sanityCheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.event.PlayListDeleted;
import musicStreaming._global.event.PlayListMusicCreated;
import musicStreaming._global.event.PlayListMusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.sanityCheck.reqDtos.LogsReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicDeletedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockPlayListDeletedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockPlayListMusicCreatedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockPlayListMusicDeletedReqDto;
import musicStreaming.sanityCheck.resDtos.LogsResDto;

@Service
@RequiredArgsConstructor
public class SanityCheckService {
    private final String logFilePath = "./logs/logback.log";

    // 출력된 로그들 중에서 끝부분 몇라인을 읽어서 반환시키기 위해서
    public LogsResDto logs(LogsReqDto logsReqDto) throws FileNotFoundException {
            List<String> logs = new ArrayList<>();

            try {
                
                CustomLogger.debug(CustomLoggerType.EFFECT, "Try to read logs", String.format("{filePath: %s}", logFilePath));
                
                Scanner myReader = new Scanner(new File(logFilePath));
                while (myReader.hasNextLine())
                {
                    String readLog = myReader.nextLine();
                    if (logsReqDto.getRegFilter().isEmpty()) logs.add(readLog);
                    else if(readLog.matches(logsReqDto.getRegFilter())) logs.add(readLog);
                }
                myReader.close();
                
                CustomLogger.debug(CustomLoggerType.EFFECT, "Read logs", String.format("{logsSize: %d}", logs.size()));

            } catch (FileNotFoundException e) {
                CustomLogger.error(e, "Error while reading logs", String.format("{filePath: %s}", logFilePath));
                throw new FileNotFoundException();
            }

            return new LogsResDto(logs.subList(Math.max(logs.size()-logsReqDto.getLineLength(), 0), logs.size()));
    }


    // Policy 테스트용으로 PlayListMusicCreated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicCreated(MockPlayListMusicCreatedReqDto mockData) {
        (new PlayListMusicCreated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListMusicDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicDeleted(MockPlayListMusicDeletedReqDto mockData) {
        (new PlayListMusicDeleted(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListDeleted(MockPlayListDeletedReqDto mockData) {
        (new PlayListDeleted(mockData)).publish();
    }

    // Policy 테스트용으로 MusicDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockMusicDeleted(MockMusicDeletedReqDto mockData) {
        (new MusicDeleted(mockData)).publish();
    }
}
