package project.ui;

import java.util.function.BiFunction;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import project.dto.UserDto;
import project.ui.town.Town;

public record TownHolder(int townId, JCheckBox townCheckBox, BiFunction<UserDto, Long, Town> townFunction) {
}
