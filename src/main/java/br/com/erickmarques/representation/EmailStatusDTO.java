package br.com.erickmarques.representation;

import br.com.erickmarques.enumeration.EmailStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailStatusDTO {
    private EmailStatus status;
}
