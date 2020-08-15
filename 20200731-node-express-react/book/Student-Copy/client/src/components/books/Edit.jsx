import React, { useState, useEffect } from 'react';
import { Form, Container } from 'react-bootstrap';
import Axios from 'axios';
import { Redirect } from 'react-router-dom';
import { toast } from 'react-toastify';

const Edit = function (props) {

  const id = props.location.state.id; // found in docs for react router

  const [inputs, setInputs] = useState({
    isbn: '',
    title: "",
    abstract: '',
    price: 10.0,
    publisher: '',
    author: null,
  });

  const [redirect, setRedirect] = useState(false);

  useEffect(() => {
    (async () => {
      const bookResp = await Axios.get(`/api/resources/${id}`);
      if (bookResp.status === 200) setInputs(bookResp.data);
    })();
  }, []);

  const handleSubmit = async event => {
    event.preventDefault();

    try {
      const body = {...inputs};
      body.id = body._id;
      delete body.author;
      delete body._id;
      const resp = await Axios.post('/api/resources/update', body);

      if (resp.status === 200) {
        toast("The book was updated successfully", {
          type: toast.TYPE.SUCCESS
        });
        setRedirect(true);
      } else {
        toast("There was an issue updating the book", {
          type: toast.TYPE.ERROR
        });
      }
    } catch (error) {
      toast("There was an issue updating the book", {
        type: toast.TYPE.ERROR
      });
    }
  };

  const handleInputChange = async event => {
    event.persist();

    const { name, value } = event.target;

    setInputs(inputs => ({
      ...inputs,
      [name]: value
    }));
  };

  if (!props.user || redirect) return (<Redirect to="/books" />);

  return (
    <Container className="my-5">
      <header>
        <h1>Edit Book</h1>
      </header>

      <hr />

      <div>
        <Form onSubmit={handleSubmit}>
          <Form.Group>
            <Form.Label>ISBN:</Form.Label>
            <Form.Control
              name="isbn"
              onChange={handleInputChange}
              value={inputs.isbn}
              readOnly={!(inputs.author && inputs.author._id == props.user._id)}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Title:</Form.Label>
            <Form.Control
              name="title"
              onChange={handleInputChange}
              value={inputs.title}
              readOnly={!(inputs.author && inputs.author._id == props.user._id)}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Author:</Form.Label>
            <Form.Control
              name="author"
              value={inputs.author ? `${inputs.author.firstName} ${inputs.author.lastName}` : ""}
              readOnly={true}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Publisher:</Form.Label>
            <Form.Control
              name="publisher"
              onChange={handleInputChange}
              value={inputs.publisher}
              readOnly={!(inputs.author && inputs.author._id == props.user._id)}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Price:</Form.Label>
            <Form.Control
              type="number"
              name="price"
              onChange={handleInputChange}
              value={inputs.price}
              readOnly={!(inputs.author && inputs.author._id == props.user._id)}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Abstract:</Form.Label>
            <textarea
              className="form-control summernote"
              name="abstract"
              onChange={handleInputChange}
              value={inputs.abstract}
              readOnly={!(inputs.author && inputs.author._id == props.user._id)}
            ></textarea>
          </Form.Group>

          <Form.Group>
            <button type="submit" className="btn btn-primary" disabled={!(inputs.author && inputs.author._id == props.user._id)}>Update</button>
          </Form.Group>
        </Form>
      </div>
    </Container>
  );

};

export default Edit;