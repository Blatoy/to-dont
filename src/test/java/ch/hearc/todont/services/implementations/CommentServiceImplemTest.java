package ch.hearc.todont.services.implementations;

/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.hearc.todont.models.Comment;
import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.CommentRepository;
import ch.hearc.todont.repositories.PledgeRepository;
import ch.hearc.todont.repositories.ToDontRepository;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.CommentService;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
*/

public class CommentServiceImplemTest {
    // TODO: We tried to implement tests for services but were not willing to spend
    // more time to figure out why mocking did not work
    
    /*
     * @TestConfiguration static class CommentServiceImplemTestContextConfiguration
     * {
     * 
     * @Bean public CommentService commentService() { return new
     * CommentServiceImplem(); } }
     * 
     * @Autowired private CommentService commentService;
     * 
     * @MockBean private PledgeRepository pledgeRepo;
     * 
     * @MockBean private UserRepository userRepo;
     * 
     * @MockBean private CommentRepository commentRepo;
     * 
     * @MockBean private ToDontRepository toDontRepo;
     * 
     * @Before public void setUp() { User alice = new User("Alice"); User bob = new
     * User("Bob"); User someoneElseModerator = new User("mod"); User
     * someoneElseNotModerator = new User("nomod");
     * Mockito.when(userRepo.findByName("bob")).thenReturn(bob);
     * Mockito.when(userRepo.findByName("mod")).thenReturn(someoneElseModerator);
     * Mockito.when(userRepo.findByName("nomod")).thenReturn(someoneElseNotModerator
     * );
     * 
     * ToDont toDontWithoutBob = new ToDont(alice, "without bob",
     * Visibility.PUBLIC); ToDont toDontWithBob = new ToDont(alice, "with bob",
     * Visibility.PUBLIC); Mockito.when(toDontRepo.findAll()) .thenReturn(new
     * ArrayList<ToDont>(Arrays.asList(toDontWithBob, toDontWithoutBob)));
     * 
     * Pledge pledge = new Pledge(bob, toDontWithBob); new
     * Pledge(someoneElseModerator, toDontWithBob);
     * toDontWithBob.addModerator(alice, someoneElseModerator);
     * 
     * Comment comment = new Comment(pledge, "salut");
     * Mockito.when(commentRepo.findAll()).thenReturn(new
     * ArrayList<Comment>(Arrays.asList( comment ))); }
     * 
     * @Test public void whenCommentingOnPledgedToDont_thenCommentShouldWork() {
     * System.out.println(userRepo); User bob = userRepo.findByName("bob"); ToDont
     * toDontWithBob = toDontRepo.findAll().get(0);
     * 
     * assertTrue(commentService.post(bob, toDontWithBob, "salut")); }
     * 
     * @Test public void whenCommentingOnNotPledgedToDont_thenCommentShouldNotWork()
     * { User bob = userRepo.findByName("bob"); ToDont toDontWithoutBob =
     * toDontRepo.findAll().get(1);
     * 
     * assertFalse(commentService.post(bob, toDontWithoutBob, "salut")); }
     * 
     * @Test public void whenDeletingOwnComment_thenShouldReturnTrue() { User bob =
     * userRepo.findByName("bob"); Comment comment = commentRepo.findAll().get(0);
     * 
     * assertTrue(commentService.delete(bob, comment)); }
     * 
     * @Test public void givenModerator_whenDeletingComment_thenShouldReturnTrue() {
     * User mod = userRepo.findByName("mod"); Comment comment =
     * commentRepo.findAll().get(0);
     * 
     * assertTrue(commentService.delete(mod, comment)); }
     * 
     * @Test public void
     * givenNotModerator_whenDeletingComment_thenShouldReturnFalse() { User nomod =
     * userRepo.findByName("nomod"); Comment comment = commentRepo.findAll().get(0);
     * 
     * assertFalse(commentService.delete(nomod, comment)); }
     */
}