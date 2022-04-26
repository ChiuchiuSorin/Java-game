package game.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "save_game")
@Data
public class SaveGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "level")
    private int level;

}
