package com.icia.member;


import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService ms;

    @Test
    @Transactional
    @Rollback
    @DisplayName("회원가입테스트")
    public void memberSaveTest(){
        final String email = "가입이메일1";
        final String password = "가입비밀번호1";
        final String name = "가입이름1";

        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(email,password,name);

     /*   MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setMemberEmail("가입이메일1");
        memberSaveDTO.setMemberPassword("가입비밀번호1");
        memberSaveDTO.setMemberName("가입이름1");*/

        Long memberId = ms.memberSave(memberSaveDTO);

    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("로그인 테스트")
    public void memberLoginTest(){
        final String email = "로그인이메일1";
        final String password = "로그인비밀번호1";
        final String name = "로그인이름1";
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(email,password,name);
        ms.memberSave(memberSaveDTO);
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO(email,password);
        boolean login = ms.login(memberLoginDTO);
        assertThat(login).isTrue();

    }





}
