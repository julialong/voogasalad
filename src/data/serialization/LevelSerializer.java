package data.serialization;

import java.io.IOException;
import java.io.FileWriter;

import com.google.gson.JsonArray;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.grid.GridCell;
import data.resources.DataFileException;

import engine.level.Level;

/**
 * @author Maya Messinger
 * Class to "serialize" and deserialze level main objects not able to be serialized with GSON
 * Adds to a file level instance variables and a ScrollingGrid
 */
public class LevelSerializer	{
	private static final String NAME = "name";
	private static final String BGCOLOR = "color";
	private static final String WIDTH = "width";
	private static final String HEIGHT = "height";
	private static final String SG = "ScrollingGrid";
	
	public LevelSerializer() {
		// empty because design checklist gets mad if constructor doesn't exist
	}

	/**
	 * @param fw	FileWriter that is linked to file to write to (this method is called when file is already being edited)
	 * @param level	Level to write out
	 */
	public void serialize(FileWriter fw, Level level, ScrollingGrid grid) throws DataFileException	{
		writeKeyValue(fw, NAME, level.getName());
		writeKeyValue(fw, BGCOLOR, level.getColor());
		writeKeyValue(fw, WIDTH, Double.toString(level.getSize()[0]));
		writeKeyValue(fw, HEIGHT, Double.toString(level.getSize()[1]));
		writeGrid(fw, grid);
	}

	private void writeKeyValue(FileWriter fw, String key, String value) throws DataFileException	{
		try	{
			TextWriter.writeKey(fw, key);
			TextWriter.writeValue(fw, value);
			TextWriter.checkWriteComma(fw, Integer.MIN_VALUE, Integer.MAX_VALUE);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			TextWriter.error(e, fw);
		}
	}

	private void writeKeyValue(FileWriter fw, String key, int value) throws DataFileException	{
		try	{
			TextWriter.writeKey(fw, key);
			TextWriter.writeValue(fw, value);
			TextWriter.checkWriteComma(fw, Integer.MIN_VALUE, Integer.MAX_VALUE);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			TextWriter.error(e, fw);
		}
	}

	private void writeGrid(FileWriter fw, ScrollingGrid grid) throws DataFileException	{
		TextWriter.startArray(fw, SG);

		for (int i = 0; i < grid.getCellArray().length; i++)	{
			TextWriter.startArray(fw, null);
			for (int j = 0; j < grid.getCellArray()[i].length; j++)	{
				TextWriter.writeValue(fw, grid.getCellArray()[i][j].getID());
				TextWriter.checkWriteComma(fw, j, grid.getCellArray()[i].length);
			}
			TextWriter.newLine(fw);
			TextWriter.closeArray(fw, i, grid.getCellArray().length);
		}

		TextWriter.closeArray(fw, 0, 0);
	}

	/**
	 * Create ScrollingGrid for a level given the JSON version of the grid
	 * @param jsonGrid	ScrollingGrid in JOSN form - 2d array containing GridCell imagepaths
	 * @return new ScrollingGrid to go itno level
	 */
	public ScrollingGrid deserialize(JsonArray jsonGrid, ScrollingGrid grid, AuthoredLevel al)	{
		grid.setMediator(al);

		for (int i = 0; i < jsonGrid.size(); i++)	{
			JsonArray row = jsonGrid.get(i).getAsJsonArray();

			for (int j = 0; j < jsonGrid.get(i).getAsJsonArray().size(); j ++)	{
				if (!row.get(j).getAsString().equals("null"))	{
					grid.setCellElement(grid.getCellArray()[i][j], row.get(j).getAsString());
				}
			}
		}

		return grid;
	}
}