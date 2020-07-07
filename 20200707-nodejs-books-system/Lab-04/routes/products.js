const { new: _new, index, show, create, edit, update, delete: _delete } = require('../controllers/ProductsController');

module.exports = router => {
  router.get('/products', index);
  router.get('/products/new', _new);
  router.post('/products', create);
  router.post('/products/update', update);
  router.post('/products/delete', _delete);
  router.get('/products/:id/edit', edit);
  router.get('/products/:id', show);
};