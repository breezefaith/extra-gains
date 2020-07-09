const { new: _new, index, show, create, edit, update, delete: _delete } = require('../controllers/PlansController');

// Step 1: Write an authentication function to identify if a request is authenticated
function auth (req, res, next) {
}

// Step 2: Add the authentication function to all the routes below

module.exports = router => {
  router.get('/plans', index);
  router.get('/plans/new', _new);
  router.post('/plans', create); 
  router.post('/plans/update', update); 
  router.post('/plans/delete', _delete); 
  router.get('/plans/:id/edit', edit); 
  router.get('/plans/:id', show);
};