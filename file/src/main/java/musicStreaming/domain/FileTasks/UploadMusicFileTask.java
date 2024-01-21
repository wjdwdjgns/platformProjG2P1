package musicStreaming.domain.FileTasks;

import musicStreaming._global.event.MusicFileUploadRequested;
import musicStreaming._global.externalSystemProxy.ExternalSystemProxyService;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming._global.resources.ResourcesService;

import musicStreaming.domain.FileRepository;

public class UploadMusicFileTask {
    // 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    public static void uploadMusicFileTask(MusicFileUploadRequested musicFileUploadRequested,
            FileRepository fileRepository, ResourcesService resourcesService,
            ExternalSystemProxyService externalSystemProxyService
        ) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: uploadMusicFile");

        // [1] 얻어진 DataUrlCode를 externalSystemProxyService를 이용해서 요청해서 실제 DataUrl을 얻습니다.
        // [2] resourcesService로 DataUrl을 저장해서 Path를 얻습니다.
        // [3] 지금까지 얻은 데이터를 기반으로 fileRepository로 File 데이터를 생성합니다.
        // [4] 총 음악의 길이를 resourcesService로 얻습니다.
        // [5] MusicFileUploaded 이벤트를 발생시킵니다.
    }
}
