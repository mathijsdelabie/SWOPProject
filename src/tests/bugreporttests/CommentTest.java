package tests.bugreporttests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.bugreports.BugReport;
import model.bugreports.bugtag.BugTag;
import model.bugreports.comments.Comment;

public class CommentTest {

	Comment comment;
	String text = "This is a comment.";
	BugReport bugReport = new BugReport(null, null, null, null, null, null, null, null, null, null, BugTag.NEW, null, null, null, null, null, null);
	@Before
	public void setUp() throws Exception {
		comment = new Comment(bugReport, text);
	}

	@Test
	public void constructorTest() {
		assertTrue(text.equals(comment.getText()));
		assertNotNull(comment.getCreationDate());
		assertNotNull(comment.getComments());
	}
	
	@Test
	public void addCommentTest() {
		assertEquals(0, comment.getComments().size());
		comment.addComment("This is a comment on a comment");
		assertEquals(1, comment.getComments().size());
	}

}
