// INSTRUCTIONS:
/*
  Create a new resource controller that uses the
  User as an associative collection (examples):
  - User -> Books
  - User -> Reservation

  The resource controller must contain the 7 resource actions:
  - index
  - show
  - new
  - create
  - edit
  - update
  - delete
*/
const viewPath = 'resources';
const Book = require('../models/Resource');
const User = require('../models/User');

exports.index = async (req, res) => {
  try {
    const books = await Book
      .find().populate('author')
      .sort({ updatedAt: 'desc' });

    res.status(200).json(books);
  } catch (error) {
    res.status(400).json({ message: 'There was an error fetching the books', error });
  }
};

exports.show = async (req, res) => {
  try {
    const book = await Book.findById(req.params.id).populate('author');

    res.status(200).json(book);
  } catch (error) {
    res.status(400).json({ message: "There was an error fetching the blog", error });
  }
};

exports.new = (req, res) => {
  res.render(`${viewPath}/new`, {
    pageTitle: 'New Book'
  });
};

exports.create = async (req, res) => {
  try {
    const { user: email } = req.session.passport;
    const user = await User.findOne({ email: email });

    const book = await Book.create({ author: user._id, ...req.body });

    res.status(200).json(book);
  } catch (error) {
    res.status(400).json({ message: "There was an error creating the book", error });
  }
};

exports.edit = async (req, res) => {
  try {
    const { user: email } = req.session.passport;
    const user = await User.findOne({ email: email });

    const book = await Book.findById(req.params.id).populate('author');
    if (book.author.id == user.id) {
      res.render(`${viewPath}/edit`, {
        pageTitle: book.title,
        formData: book
      });
    } else {
      res.redirect(`/resources/${book.id}`);
    }

  } catch (error) {
    req.flash('danger', `There was an error accessing this resource: ${error}`);
    res.redirect('/');
  }
};

exports.update = async (req, res) => {
  try {
    const { user: email } = req.session.passport;
    const user = await User.findOne({ email: email });

    let book = await Book.findById(req.body.id).populate('author');
    if (!book) throw new Error('Resource could not be found');

    const attributes = { author: user._id, ...req.body };
    await Book.validate(attributes);
    book = await Book.findByIdAndUpdate(attributes.id, attributes);

    res.status(200).json(book);
  } catch (error) {
    res.status(400).json({ message: "There was an error updating the book", error });
  }
};

exports.delete = async (req, res) => {
  try {
    await Book.deleteOne({ _id: req.body.id });
    res.status(200).json({ message: "Yay." });
  } catch (error) {
    res.status(400).jason({ message: "There was an error deleting the book" });
  }
};