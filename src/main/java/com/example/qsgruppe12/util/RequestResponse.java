package com.example.qsgruppe12.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to give simple replies to the client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Simple response class to the client")
public class RequestResponse {
    @ApiModelProperty(notes = "Response in the form of a string")
    private String requestResponse;
}
