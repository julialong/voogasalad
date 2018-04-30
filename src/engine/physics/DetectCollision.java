package engine.physics;

import engine.entity.GameEntity;

/**
 * Detects collision and collision direction Code based on:
 * https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
 * 
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class DetectCollision {

	/**
	 * Detects a collision and returns a string output of the type
	 * 
	 * @param source
	 *            - source GameEntity
	 * @param target
	 *            - target GameEntity
	 * @return top, bottom, left, right, none corresponding to direction
	 */
	public String detect(GameEntity source, GameEntity target) {
		double source_top = source.getPosition()[1];
		double source_bottom = source.getPosition()[1] - source.getSizeY();
		double source_left = source.getPosition()[0];
		double source_right = source.getPosition()[0] + source.getSizeX();

		double target_top = target.getPosition()[1];
		double target_bottom = target.getPosition()[1] - target.getSizeY();
		double target_left = target.getPosition()[0];
		double target_right = target.getPosition()[0] + target.getSizeX();

		double b_collision = -(source_bottom - target.getPosition()[1]);
		double t_collision = -(target_bottom - source.getPosition()[1]);
		double l_collision = target_right - source.getPosition()[0];
		double r_collision = source_right - target.getPosition()[0];

		if ((target_bottom <= source_top && target_bottom >= source_bottom)
				|| (target_top <= source_top && target_top >= source_bottom)
				|| (source_bottom <= target_top && source_bottom >= target_bottom)
				|| (source_top <= target_top && source_top >= target_bottom)) {

			if ((target_left >= source_left && target_left <= source_right)
					|| (target_right >= source_left && target_right <= source_right)
					|| (source_left >= target_left && source_left <= target_right)
					|| (source_right >= target_left && source_right <= target_right)) {

				if (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision) {
					return "top";
				}
				if (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision) {
					return "bottom";
				}
				if (l_collision < r_collision && l_collision < t_collision && l_collision < b_collision) {
					return "left";
				}
				if (r_collision < l_collision && r_collision < t_collision && r_collision < b_collision) {
					return "right";
				}
			}
		}
		return "none";
	}
}
