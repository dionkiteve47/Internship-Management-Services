package com.dali.security.Controller;

import com.dali.security.Entity.*;
import com.dali.security.Service.*;
import com.dali.security.Service.StreamService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/gestionformation")
public class Gestionformation {

    FormationService formationService;
    SupportService supportService;
    VideoService videoService;
    QuizzService quizzService;
    QuestionService questionService;
    StreamService streamService;




    //-----------------formation--------------//
    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation f) {
        return formationService.addFormation(f);
    }


    @PutMapping("/updateFormation/{id}")
    public Formation updateFormation(@PathVariable("id") Long id, @RequestBody Formation f) {
        f.setId(id);
        return formationService.updateFormation(f);
    }

    @GetMapping("/retrieveFormationByID/{id}")
    public Formation retrieveFormationByID( @PathVariable("id") Long id) {
        return formationService.retrieveFormationByID(id);
    }

    @DeleteMapping("/deleteFormation/{id}")
    public void deleteFormation(@PathVariable("id")Long id) {
        formationService.deleteFormation(id);
    }

    @GetMapping("/retrieveAllFormation")
    public List<Formation> retrieveAllFormation() {
        return formationService.retrieveAllFormation();
    }


    @PutMapping("/updateFormationRating/{id}")
    public Formation updateFormationRating(@PathVariable("id") Long id, @RequestParam("rating") Integer rating) {
        Formation formation = formationService.retrieveFormationByID(id);
        formation.setRating(rating);
        return formationService.updateFormation(formation);
    }














    //-----------------Support--------------//
    @PostMapping("/addSupport")
    public Support addSupport(@RequestBody Support s) {
        return supportService.addSupport(s);
    }

    @PutMapping("/updateSupport/{id}")
    public Support updateSupport(@PathVariable("id") Long id, @RequestBody Support s) {
        s.setId(id);
        return supportService.updateSupport(s);
    }

    @GetMapping("/retrieveAllSupport")
    public List<Support> retrieveAllSupport() {
        return supportService.retrieveAllSupport();
    }

    @GetMapping("/retriveSupportByID/{id}")
    public Support retrieveSupportByID(@PathVariable("id")Long id) {
        return supportService.retrieveSupportByID(id);
    }

    @DeleteMapping("/deleteSupport/{id}")
    public void deleteSupport(@PathVariable("id") Long id) {
        supportService.deleteSupport(id);
    }










    //-----------------Video--------------//
    @PostMapping("/addVideo")
    public Video addVideo(@RequestBody Video v) {
        return videoService.addVideo(v);
    }

    @PutMapping("/updateVideo/{id}")
    public Video updateVideo(@PathVariable("id") Long id, @RequestBody Video v) {
        v.setId(id);
        return videoService.updateVideo(v);
    }

    @GetMapping("/retrieveAllVideo")
    public List<Video> retrieveAllVideo() {
        return videoService.retrieveAllVideo();
    }

    @GetMapping("/retrieveVideoByID/{id}")
    public Video retrieveVideoByID(@PathVariable("id") Long id) {
        return videoService.retrieveVideoByID(id);
    }

    @DeleteMapping("/deleteVideo/{id}")
    public void deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
    }


    @PutMapping("/updateVideoLikes/{id}")
    public Video updateVideoLikes(@PathVariable("id") Long id, @RequestParam("likes") Integer likes) {
        Video video = videoService.retrieveVideoByID(id);
        video.setLikes(likes);
        return videoService.updateVideo(video);
    }


    @GetMapping("/retrieveMostLikedVideos")
    public List<Video> retrieveMostLikedVideos() {
        return videoService.retrieveMostLikedVideos();
    }








    //-----------------Quizz--------------//

    @PostMapping("/addQuizz")
    public Quizz addQuizz(@RequestBody Quizz q) {
        return quizzService.addQuizz(q);
    }

    @PutMapping("/updateQuizz/{id}")
    public Quizz updateQuizz(@PathVariable("id") Long id, @RequestBody Quizz q) {
        q.setId(id);
        return quizzService.updateQuizz(q);
    }

    @GetMapping("/retrieveAllQuizz")
    public List<Quizz> retrieveAllQuizz() {
        return quizzService.retrieveAllQuizz();
    }

    @GetMapping("/retriveQuizzByID/{id}")
    public Quizz retrieveQuizzByID(@PathVariable("id")Long id) {
        return quizzService.retrieveQuizzByID(id);
    }

    @DeleteMapping("/deleteQuizz/{id}")
    public void deleteQuizz(@PathVariable("id") Long id) {
        quizzService.deleteQuizz(id);
    }








    //-----------------Question--------------//

    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question q) {
        return questionService.addQuestion(q);
    }

    @PutMapping("/updateQuestion/{id}")
    public Question updateQuestion(@PathVariable("id") Long id, @RequestBody Question q) {
        q.setId(id);
        return questionService.updateQuestion(q);
    }

    @GetMapping("/retrieveAllQuestion")
    public List<Question> retrieveAllQuestion() {
        return questionService.retrieveAllQuestion();
    }

    @GetMapping("/retriveQuestionByID/{id}")
    public Question retrieveQuestionByID(@PathVariable("id")Long id) {
        return questionService.retrieveQuestionByID(id);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
    }









    //--------------avancées------------//

    @GetMapping("/formationvideos/{formationVideoId}")
    public ResponseEntity<List<Video>> getVideosByFormationVideoId(@PathVariable Long formationVideoId) {
        List<Video> videos = videoService.getVideosByFormationVideoId(formationVideoId);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/formationquizzs/{formationQuizzId}")
    public ResponseEntity<List<Quizz>> getQuizzsByFormationQuizzId(@PathVariable Long formationQuizzId) {
        List<Quizz> quizzs = quizzService.getQuizzsByFormationQuizzId(formationQuizzId);
        return ResponseEntity.ok(quizzs);
    }

    @GetMapping("/quizzquestions/{quizzQuestionId}")
    public ResponseEntity<List<Question>> getQuestionsByQuizzQuestionId(@PathVariable Long quizzQuestionId) {
        List<Question> questions = questionService.getQuestionsByQuizzQuestionId(quizzQuestionId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping(value = "/videodisplay/{titre}", produces = "video/mp4")
    public Mono<Resource> streamContent(@PathVariable String titre){
        return  streamService.retrieveContent(titre);
    }




    //////////////////ASSINGNING/////////
    @PostMapping("/videoassigntoformation/{id}")
    public Video addVideoAndAssignFormation(@RequestBody Video video, @PathVariable("id") long id) {
        return videoService.AddVideoAndAssign(video, id);
    }


    @PostMapping("/supportassigntoformation/{id}")
    public Support addSupportAndAssignFormation(@RequestBody Support support, @PathVariable("id") long id) {
        return supportService.AddSupportAndAssign(support, id);
    }

    @PostMapping("/quizzassigntoformation/{id}")
    public Quizz addQuizzAndAssignFormation(@RequestBody Quizz quizz, @PathVariable("id") long id) {
        return quizzService.AddQuizzAndAssign(quizz, id);
    }

    @PostMapping("/questionassigntoquizz/{id}")
    public Question addQuestionAndAssignQuizz(@RequestBody Question question, @PathVariable("id") long id) {
        return questionService.AddQuestionAndAssign(question, id);
    }

}




