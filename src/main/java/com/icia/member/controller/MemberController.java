package com.icia.member.controller;

import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String save(@Validated @ModelAttribute("save") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ms.memberSave(memberSaveDTO);
            return "/member/save";
        } else {
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
            return "/member/findAll";
        }else{
            return "redirect:/member/login";
        }
    }





}
