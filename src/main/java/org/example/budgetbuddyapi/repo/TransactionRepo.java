package org.example.budgetbuddyapi.repo;



// repo/ → how you talk to the database (repositories = CRUD helpers)
// Interface which extends TransactionDoc classes using the MongoRepository class
// This is your repository — the class you’ll use in your controllers/services to talk to the database.
// It’s declared as an interface (not a class) because Spring generates the implementation automatically at runtime.
// You don’t have to write CRUD code by hand.



import org.example.budgetbuddyapi.domain.TransactionDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<TransactionDoc, String> {}


// When you extend it, you tell Spring:
//
//T = TransactionDoc → the document type you’re storing.
//
//ID = String → the type of the primary key (@Id field in your document is a String).

// “Give me a repository for TransactionDoc documents with String IDs.”