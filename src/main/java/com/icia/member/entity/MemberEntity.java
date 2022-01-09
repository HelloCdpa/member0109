package com.icia.member.entity;

import com.icia.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 50)
    private String memberPassword;

    @Column
    private String memberName;

    // MemberSaveDTO -> MemberEntity 로
    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity member = new MemberEntity();
        member.setMemberEmail(memberSaveDTO.getMemberEmail());
        member.setMemberPassword(memberSaveDTO.getMemberPassword());
        member.setMemberName(memberSaveDTO.getMemberName());

        return member;
    }








}
