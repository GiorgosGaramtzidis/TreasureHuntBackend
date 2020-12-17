package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "LeaderBoard")
public class LeaderBoardUser {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(
           name = "UUID",
           strategy = "org.hibernate.id.UUIDGenerator"
   )
   private String id;
   private String leaderBoardName;
   private int score;


   public LeaderBoardUser(String userName,String iD) {
      this.id=iD;
      this.leaderBoardName=userName;
      this.score =0;
   }
}
