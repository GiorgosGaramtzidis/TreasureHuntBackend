package com.example.demo.model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "LeaderBoard")
public class LeaderBoardUser {

   @Id
   private String id;
   private String leaderBoardName;
   private int games;

}