package com.kcdcb.coreapi.controller;

import com.kcdcb.coreapi.Constants;
import com.kcdcb.coreapi.config.component.BaseResponse;
import com.kcdcb.coreapi.config.component.KcdCbSuccess;
import com.kcdcb.coreapi.config.properties.AppProperties;
import com.kcdcb.coreapi.entity.Member;
import com.kcdcb.coreapi.service.SampleService;
import com.kcdcb.coreapi.dto.request.GetMemberRequest;
import com.kcdcb.coreapi.dto.request.RegisterMemberRequest;
import com.kcdcb.coreapi.dto.response.MemberResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/sample/*")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    private final SampleService sampleService;
    private final AppProperties appProperties;

    @ApiOperation(value = "Get Member", notes = "Get Member")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name="id", value="Member Id", required = true)
            }
    )
    @GetMapping("/member")
    public MemberResponse getMember(@Valid GetMemberRequest getMemberRequest, HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getHeader(Constants.KCDCB_REQUEST_HEADER));
        Member member = sampleService.getMember(getMemberRequest.getId());
        return MemberResponse.of(member);
    }

    @PostMapping("/member")
    @ApiOperation(value = "Register Member", notes = "Register Member")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name="name", value="Member Name", required = true)
            }
    )
    public BaseResponse registerMember(@Valid RegisterMemberRequest registerMemberRequest, HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getHeader(Constants.KCDCB_REQUEST_HEADER));
        Member member = new Member();
        member.setName(registerMemberRequest.getName());
        Member registerMemeber = sampleService.registerMemeber(member);
        BaseResponse baseResponse = new BaseResponse(KcdCbSuccess.INSERT_SUCCESS);
        baseResponse.getResultList().add(registerMemeber);
        baseResponse.setCount(baseResponse.getResultList().size());
        return baseResponse;
    }

    @GetMapping("/members")
    @ApiOperation(value = "get Members", notes = "get All Members")
    public List<MemberResponse> getMembers(HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getHeader(Constants.KCDCB_REQUEST_HEADER));
        List<Member> memberList = sampleService.getMembers();
        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberList.
                forEach((member) -> memberResponseList.add(MemberResponse.of(member)));
        return memberResponseList;
    }

}
