package com.icia.member.controller;

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
        ms.memberSave(memberSaveDTO);
        return "/member/login";
    }



}
