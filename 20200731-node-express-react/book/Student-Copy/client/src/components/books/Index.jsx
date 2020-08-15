import React, { useEffect, useState, Fragment } from 'react';
import { Container } from 'react-bootstrap';
import Axios from 'axios';
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';

const Index = function ({ user }) {

  const [books, setBooks] = useState([]);

  useEffect(() => {
    (async () => {
      await getBooks();
    })();
  }, []);

  const getBooks = async () => {
    const booksResp = await Axios.get('/api/resources');
    if (booksResp.status === 200) setBooks(booksResp.data);
  };

  const deleteBook = async book => {
    try {
      const resp = await Axios.post('/api/resources/delete', {
        id: book._id
      });

      if (resp.status === 200) toast("The book was deleted successfully", { type: toast.TYPE.SUCCESS });

      await getBooks();
    } catch (error) {
      toast("There was an error deleting the book", { type: toast.TYPE.ERROR });
    }
  };

  return (
    <Container className="my-5">
      <header>
        <h1>Archive</h1>
      </header>

      <hr />

      <div className="content">
        {books && books.map((book, i) => (
          <div key={i} className="card my-3">
            <div className="card-header clearfix">
              <div className=" float-left">
                <h5 className="card-title">
                  {book.title}
                  {
                    typeof book.author !== 'undefined' ? (
                      <small>- {`${book.author.firstName} ${book.author.lastName}`}</small>
                    ) : null
                  }
                </h5>
              </div>

              <div className="float-right">
                <small>{book.updatedAt}</small>
              </div>
            </div>

            <div className="card-body">
              <p className="card-text" dangerouslySetInnerHTML={{ __html: book.abstract }}></p>
              <Link to={{
                pathname: "/books/edit",
                state: {
                  id: book._id
                }
              }}>{'more...'}</Link>
            </div>

            <div className="card-footer">
              {
                user ? (
                  <Fragment>

                    <Link to={{
                      pathname: "/books/edit",
                      state: {
                        id: book._id
                      }
                    }}>
                      {
                        user._id == book.author._id ? (
                          <i className="fa fa-edit"></i>
                        ) : (
                            <i className="fa fa-eye"></i>
                          )
                      }
                    </Link>

                    <button type="button" onClick={() => deleteBook(book)} disabled={user._id != book.author._id}>
                      <i className="fa fa-trash"></i>
                    </button>
                  </Fragment>
                ) : null
              }


              <div className="float-right">
                <div>${book.price}&nbsp;&nbsp;<small>published by {book.publisher}</small></div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </Container>
  );

};

export default Index;