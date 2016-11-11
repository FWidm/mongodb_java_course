use blog;
db.posts.aggregate([
	{
		$unwind: "$comments"
	},
	{
		$group: {
			_id: "$comments.email",
			num: {
				$sum: 1
			}
		}
	},
	{
		$sort: {
			num: -1
		}
	}
]);
