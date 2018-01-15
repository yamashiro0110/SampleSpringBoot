package com.example.sampleswagger.rest.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ApiModel("サンプルレスポンス")
public class SampleApiResponse {
    @ApiModelProperty("年齢")
    private Integer age;
    @ApiModelProperty("金額")
    private Integer money;
    @ApiModelProperty("名")
    private String firstName;
    @ApiModelProperty("姓")
    private String lastName;
}
