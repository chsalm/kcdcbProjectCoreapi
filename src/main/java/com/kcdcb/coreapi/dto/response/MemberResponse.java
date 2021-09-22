package com.kcdcb.coreapi.dto.response;

import com.kcdcb.coreapi.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponse {
    @ApiModelProperty(example = "1")
    private final Long id;
    @ApiModelProperty(example = "홍길동")
    private final String name;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getName());
    }
}
