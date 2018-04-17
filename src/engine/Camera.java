package engine;

import java.util.ArrayList;
import java.util.List;

import engine.entity.GameEntity;
import engine.entity.Player;

public class Camera {
	ArrayList<GameEntity> ge = new ArrayList<>();
	private double camX = 0;
	private double camY = 0;
	private double levelWidth;
	private double levelHeight;
	private double camWidth;
	private double camHeight;
	
	public Camera(double levelWidth, double levelHeight, double camWidth, double camHeight) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		this.camWidth = camWidth;
		this.camHeight = camHeight;
	}
	
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
	}
	
	public void translate(List<GameEntity> geList) {
		geList = translateToTopLeft(geList);
		geList = translateToScene(geList);
	}
	
	private List translateToTopLeft(List<GameEntity> geList) {
		for(GameEntity ge : geList) {
			if(ge instanceof Player) {
				continue;
			}
			ge.setScenePosition(ge.getPosition()[0] + levelWidth/2, -ge.getPosition()[1] + levelHeight/2);
		}
		return geList;
	}
	
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
