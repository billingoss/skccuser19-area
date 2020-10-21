package nosmokeaaa;

import nosmokeaaa.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }


    @Autowired
    AreaRepository areaRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCheckOuted_Empty(@Payload CheckOuted checkOuted){

        if(checkOuted.isMe())
        {
            System.out.println("##### LLLLL6 : " + checkOuted.toJson());


            Optional<Area> areaOptional = areaRepository.findById(checkOuted.getId());
            Area area = areaOptional.get();
            area.setStatus("VACANT");
            System.out.println("##### LLLLL7 : " + area.getAreaId());
            areaRepository.save(area);

            /*
            Optional<CheckIn> checkInOptional = checkInRepository.findById(earned.getCheckInId());
            CheckIn checkIn = checkInOptional.get();
            checkIn.setPoint(earned.getPoint());
            checkIn.setSmokingAreaId(checkIn.getSmokingAreaId());
            checkIn.setStatus("EARNED");

            checkInRepository.save(checkIn);*/

        }
    }

}
