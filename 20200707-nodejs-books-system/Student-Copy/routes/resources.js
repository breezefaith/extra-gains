const { new: _new, index, show, create, edit, update, delete: _delete } = require('../controllers/ResourceController');

function auth (req, res, next) {
}

module.exports = router => {
  router.get('/resources', index);
  router.get('/resources/new', _new);
  router.post('/resources', create); 
  router.post('/resources/update', update); 
  router.post('/resources/delete', _delete); 
  router.get('/resources/:id/edit', edit); 
  router.get('/resources/:id', show);
};