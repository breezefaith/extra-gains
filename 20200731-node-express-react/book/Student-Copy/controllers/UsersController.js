const User = require('../models/User');
const passport = require('passport');
const viewPath = 'users';
const { loginUser } = require('./SessionsController');


exports.new = (req, res) => {
  res.render(`${viewPath}/new`, {
    pageTitle: 'New User'
  });
};

exports.create = async (req, res) => {
  const userDetails = req.body;
  req.session.flash = {};
  
  try {
    // Step 1: Create the new user and register them with Passport
    let user = new User(userDetails);
    user = await User.register(user, userDetails.password);
    return loginUser(user, req, res);
  } catch (error) {
    console.error(error);
    res.status(400).json({message: 'There was an issue while registering the user.', error});
  }
};