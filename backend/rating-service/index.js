const connection = require('./database')

exports.addRating = function addRating(req, res) {
	let value = req.body.value;
	let approved = req.body.approved;
	let posting_date = req.body.posting_date;
	let comment = req.body.comment;
	let accommodation_id = req.body.accommodation_id;
	let reservator = req.body.reservator;
	connection.query("insert into rating (value, approved, posting_date, comment, accommodation_id, reservator) values (?,?,?,?,?,?)",[value, approved, posting_date, comment, accommodation_id, reservator], (err, result) => {
		if (err) res.status(400).send(err);
		else {
			res.status(200).send('added');
		}
	});
};

exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from rating", (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getAURatings = function getAURatings(req, res) {
    connection.query("select * from rating where accommodation_id="+req.query.id, (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getRatingAverage = function getRatingAverage(req, res) {
	connection.query("select avg(value), count(value) from rating where accommodation_id="+req.query.id, (err, result) => {
		if (err) res.status(400).send(err);
		else {
			res.status(200).send(result);
		}
	});
};