package com.icia.member.service;

import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;

public interface MemberService {
    public Long memberSave(MemberSaveDTO memberSaveDTO);

    boolean login(MemberLoginDTO memberLoginDTO);
}
