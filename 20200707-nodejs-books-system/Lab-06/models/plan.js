const mongoose = require('mongoose');

const PlanSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true // This must exist
  },
  strategy: {
    type: String,
    required: false
  }
}, {
  timestamps: true
});


// Validation attributes
UserSchema.virtual('title')
.get(function () {
  return this._title;
})
.set(function (value) {
  this._title = value;
});

UserSchema.virtual('titleConfirmation')
.get(function () {
  return this._titleConfirmation;
})
.set(function (value) {
  if (this.title !== value)
    this.invalidate('title', 'title and titleConfirmation confirmation must match');
  this._titleConfirmation = value;
});

// Helper attribute
UserSchema.virtual('fullname')
.get(function () {
  return `${this.firstName} ${this.lastName}`;
});

UserSchema.plugin(passportLocalMongoose, {
  usernameField: 'ppassport'
});

module.exports = mongoose.model('Plan', PlanSchema);