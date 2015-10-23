package ro.pub.cs.aipi.lab02.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ro.pub.cs.aipi.lab02.general.Constants;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Exercise01Test.class, Exercise02Test.class, Exercise03Test.class, Exercise04Test.class,
		Exercise05Test.class })

public class AllTests {

	static class DirectoryScanner implements FileVisitor<Path> {
		@Override
		public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
			try {
				Files.delete(file);
			} catch (IOException ioException) {
				System.out.format("File %s has not been deleted: %s!%n", file.getFileName(), ioException.getMessage());
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path directory, IOException ioException) {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException ioException) throws IOException {
			System.out.format("File %s has not been deleted: %s!%n", file.getFileName(), ioException.getMessage());
			return FileVisitResult.CONTINUE;
		}
	}

	@BeforeClass
	public static void clean() {
		if (Constants.DEBUG) {
			System.out.println("started cleaning...");
		}
		Path path = Paths.get(Constants.OUTPUT_DIRECTORY);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException ioException) {
				System.out.println("Could not create output directory: " + ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
		DirectoryScanner directoryScanner = new DirectoryScanner();
		try {
			Files.walkFileTree(path, directoryScanner);
		} catch (IOException ioException) {
			System.out.format("Directory could not be scanned: %s!%n", ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		}
		if (Constants.DEBUG) {
			System.out.println("finished cleaning...");
		}
	}

	public static void fileComparison(String fileName) {
		Path outputFile = Paths.get(Constants.OUTPUT_DIRECTORY + "/" + fileName);
		assertTrue("File " + fileName + " does not exist or cannot be accessed in the output directory!",
				outputFile != null && Files.isRegularFile(outputFile) && Files.isReadable(outputFile));
		Path inputFile = Paths.get(Constants.INPUT_DIRECTORY + "/" + fileName);
		assertTrue("Reference file does not exist or cannot be accessed!",
				inputFile != null && Files.isRegularFile(inputFile) && Files.isReadable(inputFile));
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader outputFileBufferedReader = Files.newBufferedReader(outputFile, charset);
				BufferedReader inputFileBufferedReader = Files.newBufferedReader(inputFile, charset)) {
			String outputFileCurrentLine = null, inputFileCurrentLine = null;
			int currentLine = 1;
			do {
				outputFileCurrentLine = outputFileBufferedReader.readLine();
				inputFileCurrentLine = inputFileBufferedReader.readLine();
				if (outputFileCurrentLine != null && inputFileCurrentLine != null) {
					assertEquals(
							"Files do not match at line " + currentLine + "\n" + "found: " + outputFileCurrentLine
									+ "instead of: " + inputFileCurrentLine,
							outputFileCurrentLine, inputFileCurrentLine);
				}
				if ((outputFileCurrentLine == null) ^ (inputFileCurrentLine == null)) {
					assertEquals(
							"Files do not match at line " + currentLine + "\n" + "found: " + outputFileCurrentLine
									+ "instead of: " + inputFileCurrentLine,
							outputFileCurrentLine, inputFileCurrentLine);
				}
				currentLine++;
			} while (outputFileCurrentLine != null && inputFileCurrentLine != null);
			System.out.println("Test passed!");
		} catch (IOException ioException) {
			fail("An exception has occurred!" + ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		}
	}
}
