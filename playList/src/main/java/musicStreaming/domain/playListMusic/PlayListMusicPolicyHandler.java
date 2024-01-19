package musicStreaming.domain.playListMusic;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;

@Service
@Transactional
public class PlayListMusicPolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}
}