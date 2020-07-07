const mongoose = require('mongoose');
const passportLocalMongoose = require('passport-local-mongoose');

const UserSchema = new mongoose.Schema({
  firstName: {
    type: String,
    required: true
  },
  lastName: {
    type: String,
    required: true
  },
  ppassport: {
    type: String,
    required: true,
    unique: true,
    dropDups: true,
    validate: [
      {
        validator: function (value) {
          return this.ppassportConfirmation === value;
        },
        message: props => `${props.value} doesn't match the passport confirmation`
      },
      {
        validator: async function (value) {
          const ppassportCount = await this.model('User').count({ ppassport: value });
          return !ppassportCount;
        },
        message: props => `${props.value} exists. Please try a new passport or login`
      }
    ]
  }
}, {
  timestamps: true,
});

// Validation attributes
// UserSchema.virtual('ppassport')
// .get(function () {
//   return this._ppassport;
// })
// .set(function (value) {
//   this._ppassport = value;
// });

UserSchema.virtual('ppassportConfirmation')
.get(function () {
  return this._ppassportConfirmation;
})
.set(function (value) {
  if (this.ppassport !== value)
    this.invalidate('ppassport', 'Passport and Passport confirmation must match');
  this._ppassportConfirmation = value;
});

UserSchema.virtual('password')
.get(function () {
  return this._password;
})
.set(function (value) {
  this._password = value;
});

UserSchema.virtual('passwordConfirmation')
.get(function () {
  return this._passwordConfirmation;
})
.set(function (value) {
  if (this.password !== value)
    this.invalidate('password', 'Password and password confirmation must match');
  this._passwordConfirmation = value;
});

// Helper attribute
UserSchema.virtual('fullname')
.get(function () {
  return `${this.firstName} ${this.lastName}`;
});

UserSchema.plugin(passportLocalMongoose, {
  usernameField: 'ppassport'
});

module.exports = mongoose.model('User', UserSchema);