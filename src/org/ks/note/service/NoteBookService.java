package org.ks.note.service;

import org.ks.note.util.NoteResult;

public interface NoteBookService {
	public NoteResult loadNoteBook(String userID);
	public NoteResult add(String bookname,String userid);
	public NoteResult updateNoteBookName(String notebookName,String notebookId);
	public NoteResult delNoteBook(String notebookid);
}
