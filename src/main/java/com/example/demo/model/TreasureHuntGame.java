package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Games")
public class TreasureHuntGame {

    @Id
    private String id;
    private String gameName;
    private String gameLocation;
    private List<User> userList;
    private List<LocationsNew> locationsNewList;
    private List<UserPosition> userPositionList;
    private GameState state;
    private User Winner;


}
