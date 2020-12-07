package com.example.demo.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LeaderBoard {

     private List<Users> leaderBoardList;

}
