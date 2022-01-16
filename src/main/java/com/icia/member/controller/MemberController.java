package com.icia.member.controller;

import com.icia.member.common.PagingConst;
import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberPagingDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm(@ModelAttribute MemberSaveDTO memberSaveDTO, Model model){
        MemberSaveDTO member = new MemberSaveDTO();
        model.addAttribute("member",member);
        return "/member/save";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
        return "/member/save";

        } else {
        try {
            ms.memberSave(memberSaveDTO);
        }catch (IllegalStateException e){
            //e.getMessage()에는 서비스에서 지정한 예외 메시지가 담겨있음.
            bindingResult.reject("emailCheck",e.getMessage());
            return "/member/save";
        }
        return "redirect:/member/login";
    }
    }

    @GetMapping("login")
    public String loginForm(){
        return "/member/login";
    }


    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session){
        if(ms.login(memberLoginDTO)){
            session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());
            return "redirect:/member/";
        }else{
            return "member/login";
        }
    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
    @GetMapping
    public String findAll(Model model){
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList",memberList);
        return "/member/findAll";
    }
    @GetMapping("{memberId}")
    public String detail(@PathVariable("memberId") Long memberId,Model model ){
        MemberDetailDTO member = ms.findById(memberId);
        model.addAttribute("member",member);
        return "/member/detail";
    }


    @PostMapping("{memberId}")
    public @ResponseBody MemberDetailDTO detail(@PathVariable long memberId){
        MemberDetailDTO member = ms.findById(memberId);
        return member;
    }

    @GetMapping
    public String paging(@PageableDefault(page=1) Pageable pageable, Model model ){
        Page<MemberPagingDTO> memberList = ms.paging(pageable);
        model.addAttribute("memberList",memberList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < memberList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : memberList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "member/paging";

    }
















}
