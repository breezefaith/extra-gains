import React, { useState } from 'react';
import { Form, Container } from 'react-bootstrap';
import Axios from 'axios';
import { Redirect } from 'react-router-dom';
import { toast } from 'react-toastify';

const New = function (props) {
  const [inputs, setInputs] = useState({
    isbn: '',
    title: "",
    abstract: '',
    price: 10.0,
    publisher: '',
    // author: null,
  });

  const [redirect, setRedirect] = useState(false);

  const handleSubmit = async event => {
    event.preventDefault();

    try {
      const resp = await Axios.post('/api/resources', inputs);

      if (resp.status === 200) {
        toast("The book was created successfully", {
          type: toast.TYPE.SUCCESS
        });
        setRedirect(true);
      } else {
        toast("There was an issue creating the book", {
          type: toast.TYPE.ERROR
        });
      }
    } catch (error) {
      toast("There was an issue creating the book", {
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
        <h1>New Book</h1>
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
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Title:</Form.Label>
            <Form.Control
              name="title"
              onChange={handleInputChange}
              value={inputs.title}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Author:</Form.Label>
            <Form.Control
              // name="author"
              value={props.user.fullname}
              readOnly={true}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Publisher:</Form.Label>
            <Form.Control
              name="publisher"
              onChange={handleInputChange}
              value={inputs.publisher}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Price:</Form.Label>
            <Form.Control
              type="number"
              name="price"
              onChange={handleInputChange}
              value={inputs.price}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Abstract:</Form.Label>
            <Form.Control
              className="summernote"
              as="textarea"
              name="abstract"
              onChange={handleInputChange}
              value={inputs.abstract}
            />
          </Form.Group>

          <Form.Group>
            <button type="submit" className="btn btn-primary">Create</button>
          </Form.Group>
        </Form>
      </div>
    </Container>
  );

};

export default New;