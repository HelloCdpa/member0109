package com.icia.member;


import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService ms;

    @Test
    @DisplayName("회원가입테스트")
    public void memberSaveTest(){
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setMemberEmail("가입이메일1");
        memberSaveDTO.setMemberPassword("가입비밀번호1");
        memberSaveDTO.setMemberName("가입이름1");
        ms.memberSave(memberSaveDTO);

    }


}
