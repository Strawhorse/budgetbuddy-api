package org.example.budgetbuddyapi.api;
import jakarta.validation.Valid;
import org.example.budgetbuddyapi.domain.TransactionDoc;
import org.example.budgetbuddyapi.dto.TransactionCreateDTO;
import org.example.budgetbuddyapi.dto.TransactionResponseDTO;
import org.example.budgetbuddyapi.repo.TransactionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// api/ → the web layer (controllers = REST endpoints that Angular will call)
// Defines REST endpoints like /api/transactions.

@RestController   //→ tells Spring “this is a web controller that returns JSON responses, not HTML pages.”
@RequestMapping("api/transactions")    // → every method inside will be rooted at /api/transactions.
public class TransactionController {

    private final TransactionRepo repo;
    public TransactionController(TransactionRepo repo) {
        this.repo = repo;

        // Now you can call repo.save(...) or repo.findById(...) inside your endpoints.
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO create(@RequestBody @Valid TransactionCreateDTO dto) {
        var doc = new TransactionDoc();
        doc.setAccountId(dto.accountId());
        doc.setAmount(dto.amount());
        doc.setBookedAt(dto.bookedAt());
        doc.setDescription(dto.description());
        doc = repo.save(doc);

        return new TransactionResponseDTO(
                doc.getId(),
                doc.getAccountId(),
                doc.getAmount(),
                doc.getBookedAt(),
                doc.getDescription());
    }




//    Method which recovers a transaction by the id that MongoDB gave it

    @GetMapping("/{id}")
    public TransactionResponseDTO get(@PathVariable String id) {
        var doc = repo.findById(id);

        return new TransactionResponseDTO(
                doc.get().getId(),
                doc.get().getAccountId(),
                doc.get().getAmount(),
                doc.get().getBookedAt(),
                doc.get().getDescription()
        );
    }




//     Method to recover all transactions
    @GetMapping
    public List<TransactionResponseDTO> listAll() {

//         TransactionDoc: // domain/ → what the data looks like in the database (Mongo “documents”)
//        Use the interface findALl method to find all items in the MongoDB database

        List<TransactionDoc> allTransactions = repo.findAll();
        List<TransactionResponseDTO> results = new ArrayList<>();

        for(TransactionDoc doc:  allTransactions){
            TransactionResponseDTO dto = new TransactionResponseDTO(
                    doc.getId(),
                    doc.getAccountId(),
                    doc.getAmount(),
                    doc.getBookedAt(),
                    doc.getDescription()
            );
            results.add(dto);
        }
        return results;
    }




//    Method to delete a transaction based on ID that MongoDB gave it
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable String id){
        repo.deleteById(id);
    }

}
