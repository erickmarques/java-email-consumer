package br.com.erickmarques.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erickmarques.enumeration.EmailStatus;
import br.com.erickmarques.representation.Email;
import br.com.erickmarques.representation.EmailStatusDTO;
import br.com.erickmarques.service.EmailClient;
import br.com.erickmarques.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailConsumer {

	@Autowired
	EmailService service;

    @Autowired
    EmailClient client;
    
    @RabbitListener(queues = "${mq.queues.sender-email}")
    public void senderEmail(@Payload String payload){
        
        Email email         = null;
        EmailStatusDTO dto  = new EmailStatusDTO();

        try {
            email = toEmailObject(payload);
            service.enviarEmail(email);
            dto.setStatus(EmailStatus.SUCCESS);
            client.setStatus(email.getId(), dto);

        } catch (Exception e){
            if (email != null) {
                dto.setStatus(EmailStatus.ERROR);
                client.setStatus(email.getId(), dto);
            }

        	log.error("Erro ao enviar email: {} ", e.getMessage());
        }
    }
    
    private Email toEmailObject(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(payload, Email.class);
    }

}
