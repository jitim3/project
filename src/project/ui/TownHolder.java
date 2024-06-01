package project.ui;

import project.dto.UserDto;
import project.ui.town.Town;

import javax.swing.JRadioButton;
import java.util.function.BiFunction;

public record TownHolder(int townId, JRadioButton townRadioButton, BiFunction<UserDto, Long, Town> townFunction) {
}
