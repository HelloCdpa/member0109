package com.icia.member.dto;

import com.icia.member.entity.MemberEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveDTO {
    @NotBlank(message = "필수입력")
    private String memberEmail;

    @Length(min = 0, max = 20, message = "0~20자 이내로 입력")
    private String memberPassword;

    private String memberName;

    // MemberEntity -> MemberSaveDTO 로
    public static MemberSaveDTO toMemberSaveDTO (MemberEntity memberEntity){
        MemberSaveDTO member = new MemberSaveDTO();
        member.setMemberEmail(memberEntity.getMemberEmail());
        member.setMemberPassword(memberEntity.getMemberPassword());
        member.setMemberName(memberEntity.getMemberName());
        return member;
    }






}
