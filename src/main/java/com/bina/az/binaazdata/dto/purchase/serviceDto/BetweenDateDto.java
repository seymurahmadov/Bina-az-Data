package com.bina.az.binaazdata.dto.purchase.serviceDto;

import lombok.Data;
import org.hibernate.exception.DataException;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class BetweenDateDto {

    Date firstDate;

    Date lastDate;
}
