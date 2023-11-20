package br.com.erickmarques.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erickmarques.representation.Email;
import br.com.erickmarques.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailConsumer {

	@Autowired
	EmailService service;
    
    @RabbitListener(queues = "${mq.queues.sender-email}")
    public void senderEmail(@Payload String payload){
    	
    	System.out.println(payload);
        
        try {
            service.enviarEmail(toEmailObject(payload));

        } catch (JsonProcessingException e){
            log.error("Erro ao converter payload para objeto email: {} ", e.getMessage());
        } catch (MessagingException e){
        	log.error("Erro no MessagingException: {} ", e.getMessage());
        } catch (Exception e){
        	log.error("Erro ao enviar email: {} ", e.getMessage());
        }
    }
    
    private Email toEmailObject(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(payload, Email.class);
    }

}
