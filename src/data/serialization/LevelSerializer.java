package data.serialization;

import java.io.IOException;
import java.io.FileWriter;

import com.google.gson.JsonArray;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.grid.GridCell;

import engine.level.Level;

/**
 * @author Maya Messinger
 * Class to "serialize" and deserialze level main objects not able to be serialized with GSON
 * Adds to a file level instance variables and a ScrollingGrid
 */
public class LevelSerializer	{
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String SIZE = "size";
	private static final String SG = "ScrollingGrid";

	private int cellSize;
	private int rowNumber;
	private int columnNumber;
	private GridCell[][] cellArray;

	/**
	 * @param fw	FileWriter that is linked to file to write to (this method is called when file is already being edited)
	 * @param level	Level to write out
	 */
	public void serialize(FileWriter fw, Level level, ScrollingGrid grid)	{
		writeKeyValue(fw, NAME, level.getName());
		writeKeyValue(fw, ID, 1);// level.getID());
		writeKeyValue(fw, SIZE, 1);// level.getSize());
		writeGrid(fw, grid);
	}

	private void writeKeyValue(FileWriter fw, String key, String value)	{
		try	{
			TextWriter.writeKey(fw, key);
			TextWriter.writeValue(fw, value);
			TextWriter.checkWriteComma(fw, Integer.MIN_VALUE, Integer.MAX_VALUE);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			error(e, fw);
		}
	}

	private void writeKeyValue(FileWriter fw, String key, int value)	{
		try	{
			TextWriter.writeKey(fw, key);
			TextWriter.writeValue(fw, value);
			TextWriter.checkWriteComma(fw, Integer.MIN_VALUE, Integer.MAX_VALUE);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			error(e, fw);
		}
	}

	private void writeGrid(FileWriter fw, ScrollingGrid grid)	{
		TextWriter.startArray(fw, SG);

		// for (int i = 0; i < grid.getCellArray().length; i++)	{
		// 	TextWriter.startArray(fw, null);
		// 	for (int j = 0; j < grid.getCellArray()[i].length; j++)	{
		// 		// TODO: UNCOMMENT LINE BELOW
		// 		//TextWriter.writeValue(fw, grid.getCellArray()[i][j].getPath());
		// 		TextWriter.checkWriteComma(fw, j, grid.getCellArray()[i].length);
		// 	}
		// 	TextWriter.newLine(fw);
		// 	TextWriter.closeArray(fw, i, grid.getCellArray().length);
		// }

		TextWriter.writeValue(fw, "grid");

		TextWriter.closeArray(fw, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Create ScrollingGrid for a level given the JSON version of the grid
	 * @param jsonGrid	ScrollingGrid in JOSN form - 2d array containing GridCell imagepaths
	 * @return new ScrollingGrid to go itno level
	 */
	public ScrollingGrid deserialize(JsonArray jsonGrid)	{
		ScrollingGrid grid = new ScrollingGrid();

		for (int i = 0; i < jsonGrid.size(); i++)	{
			JsonArray row = jsonGrid.get(i).getAsJsonArray();
			for (int j = 0; j < jsonGrid.get(i).getAsJsonArray().size(); j ++)	{
//				grid.setCellImage(grid.getCellArray()[i][j], row.get(j).getAsString());
			}
		}

		return grid;
	}
}