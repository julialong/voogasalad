package engine;

import java.util.ArrayList;
import java.util.List;

import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Controls the display coordinates of a level to be centered around the player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Camera {
	ArrayList<GameEntity> player = new ArrayList<>();
	private double camX = 0;
	private double camY = 0;
	private double levelWidth;
	private double levelHeight;
	private double camWidth;
	private double camHeight;
	
	/**
	 * Instantiates the camera that calculates display coordinates.
	 * @param levelWidth
	 * @param levelHeight
	 * @param camWidth
	 * @param camHeight
	 */
	public Camera(double levelWidth, double levelHeight, double camWidth, double camHeight) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		this.camWidth = camWidth;
		this.camHeight = camHeight;
	}

	/**
	 * Updates the camera position based on the player's position
	 * @param player
	 */
	public void setPlayerPosition(GameEntity player) {
		player.setScenePosition(camWidth/2 - player.getSizeX()/2, camHeight/2 - player.getSizeY()/2);
		double playerMiddleX = player.getPosition()[0] + player.getSizeX()/2 + levelWidth/2;// - camX;
		double playerMiddleY = -player.getPosition()[1] + player.getSizeY()/2 + levelHeight/2;// - camY;
		camX = playerMiddleX - camWidth/2;
		camY = playerMiddleY - camHeight/2;
		if(camX < 0) {
			camX = 0;
			player.setScenePosition(player.getPosition()[0] + levelWidth/2 - camX, player.getScenePosition()[1]);
		}
		if(camX + camWidth > levelWidth) {
			camX = levelWidth - camWidth;
			player.setScenePosition(player.getPosition()[0] + levelWidth/2 - camX, player.getScenePosition()[1]);
		}
		if(camY < 0) {
			camY = 0;
			player.setScenePosition(player.getScenePosition()[0], -player.getPosition()[1] + levelHeight/2 - camY);
		}
		if(camY + camHeight > levelHeight) {
			camY = levelHeight - camHeight;
			player.setScenePosition(player.getScenePosition()[0], -player.getPosition()[1] + levelHeight/2 - camY);
		}
		System.out.println("Entity: " + player.getElementID() + "\nScene X: " + player.getScenePosition()[0] + "\nScene Y: " + player.getScenePosition()[1]);
	}
	
	/**
	 * Translates all entities other than the player to visual coordinates
	 * @param geList
	 */
	public void translate(List<GameEntity> geList) {
		geList = translateToTopLeft(geList);
		geList = translateToScene(geList);
	}
	
	/**
	 * Helper method to translate that does an intermediary translation step
	 * @param geList
	 * @return List of GameEntity(s)
	 */
	private List translateToTopLeft(List<GameEntity> geList) {
		for(GameEntity ge : geList) {
			if(ge instanceof Player) {
				continue;
			}
			ge.setScenePosition(ge.getPosition()[0] + levelWidth/2, -ge.getPosition()[1] + levelHeight/2);
		}
		return geList;
	}
	
	/**
	 * Helper method to translate that does an final translation step
	 * @param geList
	 * @return List of GameEntity(s)
	 */
	private List translateToScene(List<GameEntity> geList) {
		for(GameEntity ge : geList) {
			if(ge instanceof Player) {
				continue;
			}
			ge.setScenePosition(ge.getScenePosition()[0] - camX, ge.getScenePosition()[1] - camY);
		}
		return geList;
	}
}
