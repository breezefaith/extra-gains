// Fill in the missing code
import React from 'react';
import { useState, useEffect } from 'react';
import { Container, Form } from 'react-bootstrap';
import { toast } from 'react-toastify';
import Axios from 'axios';

const New = function () {
  const [inputs, setInputs] = useState({
    title: '',
    tourType: null,
    groupSize: 1,
    date: new Date().toLocaleDateString()
  });

  const [tourTypes, setTourTypes] = useState([]);

  useEffect(() => {
    async function getData(){
      try {
        const resp = await Axios.get('/api/tours/tourTypes');
        if (resp.status === 200) {
          setTourTypes(resp.data);
          setInputs(inputs => ({
            ...inputs,
            tourType: tourTypes[0]
          }));
        } else {
          toast("There was an issue.", {
            type: toast.TYPE.ERROR
          });
        }
      } catch (err) {
        toast(err.response.data.message, {
          type: toast.TYPE.ERROR
        });
      }
    };

    getData();
  }, []);  

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const resp = await Axios.post('/api/tours', inputs);
      if (resp.status === 200) {
        toast('Added tour successfully', {
          type: toast.TYPE.SUCCESS
        });
      } else {
        toast("There was an issue.", {
          type: toast.TYPE.ERROR
        });
      }
    } catch (err) {
      toast(err.response.data.message, {
        type: toast.TYPE.ERROR
      });
    }
  }
  function handleInputChange(event) {
    event.persist();

    const { name, value } = event.target;

    setInputs(inputs => ({
      ...inputs,
      [name]: value
    }));
  };


  return (
    <Container className="my-5">
      <header>
        <h1>New Tour</h1>
      </header>

      <hr />

      <div>
        <Form onSubmit={handleSubmit}>
          <Form.Group>
            <Form.Label>Title:</Form.Label>
            <Form.Control
              name="title"
              onChange={handleInputChange}
              value={inputs.title}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Tour Type:</Form.Label>
            <Form.Control
              as="select"
              name="tourType"
              onChange={handleInputChange}
              defaultValue={inputs.tourType}
            >
              {tourTypes.map((type, i) => (
                <option key={i} value={type}>{type}</option>
              ))}
            </Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Group Size:</Form.Label>
            <Form.Control
              type="number"
              name="groupSize"
              step="1"
              min="1"
              max="10"
              onChange={handleInputChange}
              value={inputs.groupSize}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Date:</Form.Label>
            <Form.Control
              type="date"
              name="date"
              onChange={handleInputChange}
              value={inputs.date}
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