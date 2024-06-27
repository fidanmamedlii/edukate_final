//package com.example.edukate.service;
//
//
//import com.example.edukate.dtos.replydto.ContactCommentDto;
//import com.example.edukate.models.ContactMessage;
//import jakarta.transaction.Transactional;
//
//import java.util.List;
//@Transactional
//public interface ContactMessageService {
//    public void saveMessage(ContactMessage message);
//    public void saveMessage1(ContactCommentDto message);
//    public List<ContactMessage> getAllMessages();
//    public ContactMessage replyToMessage(Long id, String reply);
//
//}
package com.example.edukate.service;


import com.example.edukate.dtos.replydto.ContactCommentDto;
import com.example.edukate.models.ContactMessage;

import java.util.List;

public interface ContactMessageService {
    public void saveMessage(ContactMessage message);
    public List<ContactMessage> getAllMessages();
    public ContactMessage replyToMessage(Long id, String reply);

}

